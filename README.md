# IFG Assurance - Katalon API and Kafka Test

Project ini dibuat untuk technical test IFG Assurance.

## Scope

- RESTful API testing dengan Katalon sebagai producer dan consumer.
- Kafka testing dengan Katalon sebagai consumer.
- Test data bebas memakai public fake API dan Kafka lokal.

## Prasyarat

- Katalon Studio atau Katalon Runtime Engine.
- Docker Desktop untuk menjalankan Kafka lokal via Redpanda.
- Internet saat pertama kali menjalankan test Kafka jika Katalon perlu mengunduh dependency `kafka-clients` dari annotation `@Grab`.

Jika environment Katalon tidak mengizinkan `@Grab`, download jar berikut dan taruh di folder `Drivers` project:

- `org.apache.kafka:kafka-clients:3.7.0`
- dependency transitive Kafka clients yang diminta Katalon saat compile

## Cara menjalankan REST API test

1. Buka file `IFG_Assurance_Katalon_Test.prj` di Katalon Studio.
2. Buka test suite `Test Suites/IFG Assurance API and Kafka`.
3. Jalankan test case REST:
   - `Test Cases/REST/Producer Create Policy`
   - `Test Cases/REST/Consumer Get Policy`
   - `Test Cases/REST/End-to-End Producer Consumer`

Public API yang dipakai:

```text
https://jsonplaceholder.typicode.com
```

REST producer melakukan `POST /posts` untuk mensimulasikan pembuatan policy. REST consumer melakukan `GET /posts/{id}` dan validasi response contract.

## Cara menjalankan Kafka consumer test

1. Jalankan broker dan seed message:

```bash
docker compose up -d
```

2. Pastikan topic `ifg-policy-events` berisi message:

```bash
docker compose exec redpanda rpk topic consume ifg-policy-events --brokers localhost:9092 -n 1
```

3. Jalankan test case:

```text
Test Cases/Kafka/Consumer Verify Event
```

Test ini subscribe ke topic `ifg-policy-events`, membaca message dengan key `POL-IFG-001`, lalu memvalidasi payload mengandung event `POLICY_CREATED`.

## Katalon Runtime Engine command

Contoh command:

```bash
katalonc -noSplash -runMode=console \
  -projectPath="$(pwd)/IFG_Assurance_Katalon_Test.prj" \
  -retry=0 \
  -testSuitePath="Test Suites/IFG Assurance API and Kafka" \
  -browserType="Web Service"
```

## Struktur utama

```text
Profiles/default.glbl
Object Repository/REST/*.rs
Keywords/kafka/KafkaConsumerKeyword.groovy
Test Cases/REST/*.tc
Test Cases/Kafka/*.tc
Test Suites/IFG Assurance API and Kafka.ts
docker-compose.yml
```

## Catatan

- `jsonplaceholder.typicode.com` menerima POST dan mengembalikan response `201`, tetapi data tidak benar-benar disimpan permanen. Karena itu skenario end-to-end REST memvalidasi contract dari response producer dan consumer secara eksplisit.
- Kafka memakai Redpanda karena kompatibel dengan Kafka API dan ringan untuk technical test lokal.
