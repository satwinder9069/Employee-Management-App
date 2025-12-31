# 📱 Employee Management Admin App (Android)

## Project Overview
This project is an **admin-side Android application** designed to manage employees and track attendance. The primary focus is on building a clean, maintainable, and scalable codebase using modern Android development practices.

The application currently supports core employee management workflows and a structured UI foundation. Several key features - such as attendance persistence, authentication, and leave management - are actively being developed and refined.

---
## Screen & Features
### Implemented
#### 👥 Employee Management
- Employee list with search functionality
- Department-based filtering
- Add/Edit employee using a *single reusable form*
- Full CRUD support

#### 🧑‍💼 Employee Details
- Employee profile and basic information
- Attendance history UI
- Data flow support for attendance where available

#### 📊 Dashboard
- Dashboard UI with summary cards
- State-driven UI
- Foundation for high-level insights

#### 🧱 UI & Architecture Foundation
- Modularized screens and reusable UI components
- Clean and consistent package structure
- Stable state management using StateFlow
- Proper separation of concerns

## 📈 In Progress
- Attendance form and persistence logic
- Leave management workflows
- Authentication (Login / Signup)
- Settings screen
- Full dashboard data integration with ViewModels

## 🛠️ Tech Stack
- Kotlin
- Jetpack Compose (Material 3)
- MVVM Architecture
- Room Database
- Kotlin Coroutines & Flow
- Hilt (Dependency Injection)
- Navigation Compose

## 🏗️ Architecture

The app follows a modern Android architecture focused on testability, scalability, and clean data flow.
   -  MVVM for UI state and presentation logic
   -  Repository pattern as the data abstraction layer
   -  Room as a single source of truth
   -  Reactive data streams using Flow and StateFlow
   -  Compose UI observes state directly from ViewModels

## 🔁 Data Flow
Room (DAO) -> Repository -> ViewModel (StateFlow) -> Compose UI

## 🗂️Project Structure
```
data/
 ├─ local/
 ├─ remote/
 ├─ repository/
model/
navigation/
ui/
 ├─ components/
 ├─ screens/
 ├─ theme/
 ├─ utils/
viewmodel/
EmployeeApp.kt
MainActivity.kt
```

## ▶️ How to Run

### 📌 Prequisites
- Android Studio (latest stable version)
- Android SDK
- Emulator or physical Android device

### 🚀 Steps
1. Clone the repository:
```
git clone https://github.com/satwinder9069/Employee-Management-App.git
cd Employee-Management-App
```
2. Open the project in Android Studio (latest stable version).
3. ** 🔄 Sync Gradle**
When opening the project, Android Studio will automatically sync Gradle. If prompted, click Sync Now. 
4. ** ▶️Run the app**
      - Select a virtual device (emulator) or a physical Android device (API level 21+).
      - Run → Run ‘app’ (Shift+F10 / ⌘+R).

## 🔮 Future Improvements
- Complete attendance creation and editing flow
- Connect all dashboard metrics to ViewModels
- Add authentication and session handling
- Implement leave management features
- Add settings and configurable admin preferences
- Improve validation and UX for reusable forms
- Improve offline-first behavior:
   - Conflict handling
   - Database migrations
   - Edge-case management
