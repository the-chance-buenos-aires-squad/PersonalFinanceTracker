# Personal Finance Tracker

A Kotlin-based command-line application for tracking personal finances, managing transactions, and monitoring your
financial balance.

## Features

- Track income and expenses
- Categorize transactions
- Monitor account balance
- Store transaction history
- Simple command-line interface

## Project Structure

```
src/
├── cli/           # Command-line interface components
├── checks/        # Test and validation checks
├── data_source/   # Data storage implementations
├── model/         # Core data models
├── manager/       # Business logic and data management
├── util/          # Utility functions
└── Main.kt        # Application entry point
```

## Core Components

### Data Models

- `Transaction`: Represents financial transactions with properties like amount, category, type, and date
- `TransactionCategory`: Enumeration of transaction categories
- `TransactionType`: Enumeration of transaction types (INCOME/EXPENSE)

### Data Management

- In-memory transaction storage
- Transaction management and retrieval
- Balance calculation and tracking

## Getting Started

### Building the Project

1. Clone the Manager
2. Navigate to the project directory
3. Build the project using your preferred build tool

### Running the Application

Run the main function in `Main.kt` to start the application.

## Usage

The application provides a command-line interface for:

- Adding new transactions
- Viewing transaction history
- Checking account balance
- Managing transaction categories

## Testing

The project includes validation checks in the `checks` directory to ensure proper functionality of core features.

## Contributing

1. Fork the Manager
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under The Chance License - see the LICENSE file for details. 