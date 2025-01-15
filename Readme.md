# Cloud Developer Home Assessment - API Retry Mechanism

This repository contains a simple and reliable data delivery system using a retry mechanism between two microservices, designed with Spring Boot.

## Project Overview

The project includes two microservices:

1. **Microservice-1**:
    - Reads data from a FIFO queue (e.g., Kafka, GCP Pub/Sub, MQTT Broker).
    - Sends data to Microservice-2 using a POST API.
    - Retries delivery in case of failures.

2. **Microservice-2**:
    - Provides a POST API to receive data.
    - Processes and stores the received data.

### Key Features
- Reliable message delivery with unlimited retries.
- Retry policy: retry every 10 seconds.
- FIFO queue ensures message order is preserved.
- Persistent storage tracks retries and message states.

## Technologies Used

- **Spring Boot**: For building microservices.
- **Queue**: FIFO messaging broker (e.g., Kafka, GCP Pub/Sub).
- **Database**: For persisting data (e.g., PostgreSQL, MySQL).
- **Docker**: To containerize the services.

## Prerequisites

- Java JDK 11 or later
- Maven 3.6 or later
- Docker and Docker Compose

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd <repository-name>
```

### 2. Build the Microservices
Navigate to each microservice directory and build the applications:
```bash
./mvnw clean package
```

### 3. Configure Environment Variables
Update the configuration files (e.g., `application.properties`) to set up:
- Queue connection details
- Database connection details
- Retry intervals and policies

### 4. Start the Services with Docker Compose
Run the following command to start all services:
```bash
docker-compose up --build
```

### 5. Test Kafka Producer
Send messages to the `retry-topic` using the following command:
```bash
docker exec -it <kafka-container-name> kafka-console-producer.sh \
--broker-list localhost:9092 \
--topic retry-topic
```
Replace `<kafka-container-name>` with the name or ID of your Kafka container. Once started, type your message and press Enter to send.

### 6. Access the Services
- **Microservice-1**: Available at `http://localhost:8080`
- **Microservice-2**: Available at `http://localhost:8081`

## Retry Mechanism

In Microservice-1:
- Failed API calls are logged and persisted.
- A scheduled task retries failed deliveries every 10 seconds.
- Retry continues until the delivery succeeds.

## Logs and Monitoring
- Access logs for each microservice in their respective containers.
- Use the following command for real-time logs:
```bash
docker-compose logs -f
```


For any queries or issues, feel free to reach out!

