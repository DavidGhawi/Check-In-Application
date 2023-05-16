**Mobile Development 2022/23 Portfolio**
# Requirements

Student ID: `21049134`

**Functional Requirements**

-
-
-
-
-
-
-
-
-
-

**Non-Functional Requirements**

- The application must be locked in portrait mode except for the management portal view which can be used in landscape mode.
- The font used in the application must be consistent across all activities to ensure a uniform user experience.
- The application must maintain a consistent styling and color theme to enhance user interface and user experience.
- The application shall scale the font in accordance with the user’s font size preference, ensuring accessibility for all users.
- Upon the first launch, the application shall prompt users to input their employee ID for personalized experience.
- Once the employee ID is entered, the application must fetch and display the previous check-ins within 5 seconds to maintain a responsive interface.
- If an employee ID is not found or entered incorrectly, the application must navigate to a help activity within 2 seconds.
- When a user rates their emotion for the first time in a day, the application must add a visual marker on the calendar view, animating towards that marker with a duration of 3000ms.
- The calendar view should support landscape mode for better visibility of the entire month's check-ins.
- When the user submits their daily check-in, the device shall provide haptic feedback for 50ms to acknowledge the submission.
- On submission of the daily check-in, the application must save the check-in data in the Room database immediately to ensure data consistency.
- When the management navigates to the employee's wellbeing page, the application must display a loading fragment until the data is fetched.
- Once the data for the employee's wellbeing page is received, the application must display a chart or graph of the emotional trends within 3 seconds to maintain a responsive interface.
