# 🏕 Campgrounds Explorer  
AND102 – Lab 4

A modern Android application that displays campgrounds using a RecyclerView and navigates to a detailed screen with images and location data.

---

## 📱 Demo

### Main Screen
Displays a scrollable list of campgrounds with image thumbnails, names, coordinates, and descriptions.

### Detail Screen
Shows a larger image, full description, and geographic coordinates.

> (You can add screenshots here later by uploading images to your repo and linking them.)

---

## 🚀 Features

### ✅ Core Features
- Fetches campground data from a remote API
- Parses nested JSON response
- Displays data using **RecyclerView**
- Loads images using **Glide**
- Navigates between screens using **Intents**
- Passes full campground object to DetailActivity
- Displays:
  - Name
  - Latitude & Longitude
  - Description
  - Image

---

## 🛠 Tech Stack

- **Kotlin**
- **RecyclerView**
- **ConstraintLayout**
- **AsyncHttpClient**
- **Glide**
- **Android Intents**
- **Serializable data transfer**
- **Git & GitHub**

---

## 📂 Project Structure

```
app/
 └── src/main/java/com/codepath/campgrounds/
      ├── Campground.kt
      ├── CampgroundResponse.kt
      ├── CampgroundAdapter.kt
      ├── MainActivity.kt
      └── DetailActivity.kt

res/layout/
 ├── activity_main.xml
 ├── activity_detail.xml
 └── item_campground.xml
```

---

## 🧠 Concepts Practiced

- Working with nested JSON models
- Creating and binding RecyclerView adapters
- Passing Serializable objects between Activities
- Handling Android lifecycle events
- Debugging runtime crashes
- Managing AndroidManifest configuration
- Using Git for version control

---

## 🎯 Learning Outcomes

By completing this lab, I learned how to:

- Consume and parse API data
- Build scalable UI components
- Implement navigation between screens
- Load remote images efficiently
- Structure an Android project cleanly

---

## 📦 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/stevesekapin/and102-lab4-starter.git
   ```

2. Open in Android Studio

3. Build & Run on emulator or device

---

## 👤 Author

**Steve Sekapin**  
Computer Science Student  

---

## 📌 Course

AND102 – Android Development  
CodePath Android Program

https://imgur.com/a/vD1WMiC.gif
