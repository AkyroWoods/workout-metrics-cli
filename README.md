# Lift Metrics

Lift Metrics is a command-line workout tracker that lets you log exercises and view useful training stats. Each workout records the exercise name, weight, reps, sets, and muscle group. The program automatically classifies exercises into **Push**, **Pull**, or **Legs** and calculates total volume and percentage breakdowns for each category.

---

## Features

- Log exercises with weight, reps, sets, and muscle group  
- Automatic Push/Pull/Legs classification  
- Total volume calculation for each exercise  
- Volume and percentage breakdowns by PPL category  
- Simple command-line interface  
- Maven project structure for easy setup  
- JSON save/load support coming soon  

---

## How It Works

1. You enter exercises for a workout.  
2. The program groups them into Push, Pull, or Legs based on the muscle group.  
3. It calculates total volume using weight × reps × sets.  
4. It shows how much of your workout came from each category, both in raw volume and percentages.

---

## Planned Additions

- JSON persistence for saving and loading workout history  
- Optional weekly or monthly breakdowns  
- Potential GUI or web interface in the future  
