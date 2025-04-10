# Journal

This repository contains a Java-based application designed for managing and maintaining a personal journal. It provides features to store, retrieve, and manage journal entries efficiently. The application integrates with Redis and MongoDB for storage and caching, and includes email functionality for notifications or sharing entries.

## Features

- Create, edit, and delete journal entries.
- Search functionality to find specific entries.
- Organize entries by date or tags.
- Secure and private storage for sensitive information.
- Email notifications for reminders or journal sharing.
- High performance using Redis caching.
- Persistent storage with MongoDB.

## Technologies Used

- **Language**: Java
- **Build Tool**: Maven
- **Database**: MongoDB
- **Cache**: Redis
- **Email Integration**: SMTP or other email service providers

## Getting Started

Follow the steps below to set up and run the project locally:

### Prerequisites

Ensure you have the following installed on your machine:
- Java Development Kit (JDK) 8 or above
- Maven
- MongoDB
- Redis
- An email service provider with SMTP credentials (e.g., Gmail, SendGrid, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MandarNalhe/Journal.git
2. Navigate to Project directory :
   ```bash
   cd Journal
3. Build the project using Maven:
   ```bash
   mvn clean install
4. Set up configuration in src/main.resource/application.properties
5. Run the Application
