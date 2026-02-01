## About

Workout Metrics CLI is a command‑line tool for tracking workouts and calculating training volume. You can log exercises with sets, reps, weight, and muscle group, and the program automatically classifies them into Push, Pull, and Legs categories. It also provides analytics such as your top and bottom three exercises by volume percentage and your highest‑volume exercise overall. Workouts can be saved to JSON files, loaded later, and compared side‑by‑side to see how your training sessions differ.

---

## Tech Stack
- Java 25
- Maven (build + dependency management)
- JUnit (unit testing)
- Jackson (JSON serialization)

---

##  Highlights

- Log exercises with sets, reps, weight, and muscle group  
- Automatic Push / Pull / Legs classification  
- Volume calculations for each exercise and the entire workout  
- JSON‑based save/load system with filename sanitization
- Automatic filename‑duplication checks to prevent overwriting saved workouts
- Side‑by‑side workout comparison  
- Easy to navigate CLI experience
  
---

### Why this project exists

I built this to practice:
- strengthening my core Java and OOP fundamentals
- working with files and JSON for saving and loading data
- applying clean architecture principles in a small application
- organizing code into clear, meaningful packages
- building a predictable, user‑friendly command‑line interface
- implementing analytics on top of a simple workout data model

---

##  Usage

Workout Metrics CLI is menu‑driven and easy to navigate.
From the main menu, you can create new workouts, load or list saved workouts, compare sessions, or delete old ones. Once a workout is loaded, you can add, edit, or remove exercises, view summaries and analytics, and save your progress before returning to the main menu.

---

##  Screenshots

### Main Menu
![Main Menu](images/Main-Menu.png)

### Analytics Output
![Analytics](images/Analytics.png)

Top and bottom exercises by volume percentage, P/P/L distribution, and highest‑volume movement.

### Workout Comparison
![Comparison](images/Comparison.png)

Summary of both workouts, which one had higher volume, their shared exercises, and the movements unique to each.

---

## Installation

### Clone the repository:

```bash
git clone https://github.com/AkyroWoods/Workout-Metrics-CLI.git
cd Workout-Metrics-CLI
```

### Build the project with Maven
```bash
mvn clean package
```

### Run the program
``` bash
java -jar target/workout-metrics-cli.jar
```

