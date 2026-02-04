# 📱 Android Employee Management System

A modern Android application for comprehensive employee lifecycle management, attendance tracking, and organizational analytics.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-purple.svg)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Android-24%2B-green.svg)](https://android.com)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-blue.svg)](https://developer.android.com/jetpack/compose)
[![Firebase](https://img.shields.io/badge/Firebase-Latest-orange.svg)](https://firebase.google.com)

---
## ✨ Features

### 🔐 Authentication & Security
- Firebase Authentication with email/password
- Email verification and password reset
- Persistent login with automatic session management
- Secure logout with complete navigation back‑stack clearance

### 👥 Employee Management
- Complete CRUD operations (Create, Read, Update, Delete)
- Advanced form validation with real-time user feedback using StateFlow
- Duplicate prevention on Employee ID, Email, and Phone
- Search and filter functionality
- Department-wise categorization

### ⏰ Attendance Tracking
- Daily attendance marking with timestamp
- Check-in/Check-out workflow
- Duplicate prevention (one entry per employee per day)
- Attendance status: Present, Late, On Leave (extensible for Absent)
- Historical attendance with date filtering

### 📊 Dashboard & Analytics
- Real-time statistics (Total employees, Present, On leave, Late)
- Department-wise employee distribution
- Basic attendance statistics with scope for weekly/monthly analytics
- Recent activities feed

### ⚙️ Settings & Profile
- User profile with verification status
- Account settings and preferences
- Theme customization
- Logout with confirmation

## 🛠️ Tech Stack

### Architecture
- **MVVM** (Model-View-ViewModel)
- **Clean Architecture** with separation of concerns
- **Repository Pattern** for data abstraction

### Libraries & Tools
- **UI:** Jetpack Compose, Material Design 3
- **Database:** Room (SQLite) for local data
- **Authentication:** Firebase Auth
- **Dependency Injection:** Dagger Hilt
- **Asynchronous:** Kotlin Coroutines, Flow, StateFlow
- **Navigation:** Navigation Compose
- **Date/Time:** Java Time API

### Key Android Components
- ViewModel
- LiveData/StateFlow
- Navigation Component
- Bottom Navigation
- Material Components

## 🏗️ Project Structure
```
app/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   ├── entity/
│   │   └── database/
│   └── repository/
├── di/                     # Dependency Injection modules
├── ui/
│   ├── screens/
│   │   ├── auth/          # Login, SignUp
│   │   ├── dashboard/     # Dashboard
│   │   ├── employee/      # Employee list, form
│   │   ├── attendance/    # Attendance tracking
│   │   └── settings/      # Settings
│   └── components/        # Reusable UI components
├── navigation/            # Navigation graph
├── viewmodel/            # ViewModels
└── utils/                # Utility classes
```
## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 17
- Android SDK API 24+
- Firebase account

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/satwinder9069/android-employee-management.git
cd android-employee-management
```

2. **Open in Android Studio**
   - Open Android Studio
   - File → Open → Select the project folder

3. **Firebase Setup**
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Create a new project
   - Add Android app with package name: `com.employeedb.employeedatabase`
   - Download `google-services.json`
   - Place it in `app/` folder

4. **Enable Firebase Authentication**
   - In Firebase Console → Authentication
   - Enable Email/Password sign-in method

5. **Sync Gradle**
   - Click "Sync Now" when prompted
   - Wait for dependencies to download

6. **Run the App**
   - Connect Android device or start emulator
   - Click Run button (green triangle)

## 📱 Minimum Requirements

- Android 7.0 (API 24) or higher
- Internet connection required for authentication features
- 50 MB free storage

## 🎯 Key Features Implementation

### Duplicate Prevention
- **Database Level:** Unique constraints on Employee ID, Email, Phone
- **DAO Level:** Query methods to check existence
- **Repository Level:** Validation before insert/update
- **UI Level:** User-friendly error messages

### State Management
```kotlin
// ViewModel
private val _uiState = MutableStateFlow(EmployeeUiState())
val uiState: StateFlow<EmployeeUiState> = _uiState

// UI
val state by viewModel.uiState.collectAsState()
```

### Navigation Flow:
```
      Login / SignUp
           ↓
   ├── Dashboard
   ├── Employees
   │     ├── Employee Form (Add / Edit)
   │     └── Employee Detail
   │
   ├── Attendance
   │     └── Mark / View Attendance
   │
   └── Settings
```

## 🧪 Testing

### Manual Testing Checklist
- [x] User registration and login
- [x] Email verification
- [x] Password reset
- [x] Add employee with validation
- [x] Edit employee
- [x] Delete employee
- [x] Mark attendance
- [x] Prevent duplicate attendance
- [x] Dashboard statistics
- [x] Search employees
- [x] Logout functionality

### Future Testing Plans
- Unit tests with JUnit
- UI tests with Compose Testing
- Integration tests
- CI/CD with GitHub Actions

## 📝 Future Enhancements

- [ ] Leave management system
- [ ] Payroll module
- [ ] Performance reviews
- [ ] Document upload
- [ ] Push notifications
- [ ] Dark theme
- [ ] Export reports (PDF/Excel)
- [ ] Cloud sync with Firestore

## 👨‍💻 Author

**Satwinder Kaur**
- GitHub: [@satwinder9069](https://github.com/satwinder9069)
- LinkedIn: [Satwinder Kaur](https://linkedin.com/in/satwinder-chauhan)
