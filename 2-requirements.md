**Mobile Development 2022/23 Portfolio**
# Requirements

Student ID: `21049134`

**Functional Requirements**

- The application must allow logged in users to navigate using the Bottom Navigation bar.
- If the user submitted more than 2 check ins a graph must be shown.
- If the user has not submitted enough check ins (less than 2) a message should appear informing the user that there are not enough check ins to produce the graph.
- The application should only save the rating if the user has clicked the submit button.
- When the user clicks the average button, the average should be displayed as a toast.
- The application should automatically redirect the user to the login page once registration is successful.
- The application should ignore case sensitive characters.
- If the user logins in successfully, the application should be able to fetch previous check ins and use them in the graph and average.
- The application should have a clean and modern user interface.
- The application should deny a repeat check in; if the user attempts to check in twice a day, a toast should appear denying that request.
- The application should send a notification at 16:00 local time to remind the user to submit a check in for the day.
- Each user should have a specific ID that is saved in the Room database.

**Non-Functional Requirements**

- The application must be locked in portrait mode except for the management portal view which can be used in landscape mode.
- The font used in the application must be consistent across all activities to ensure a uniform user experience.
- The application must maintain a consistent styling and color theme to enhance user interface and user experience.
- The application shall scale the font in accordance with the user’s font size preference, ensuring accessibility for all users.
- If an employee ID is not found or entered incorrectly, the application must navigate to a help activity within 2 seconds.
- When a user rates their emotion for the first time in a day, the application must add a visual marker on the calendar view, animating towards that marker with a duration of 3000ms.
- The calendar view should support landscape mode for better visibility of the entire month's check-ins.
- When the user submits their daily check-in, the device shall provide haptic feedback for 50ms to acknowledge the submission.
- On submission of the daily check-in, the application must save the check-in data in the Room database immediately to ensure data consistency.
- When the management navigates to the employee's wellbeing page, the application must display a loading fragment until the data is fetched.
- Once the data for the employee's wellbeing page is received, the application must display a chart or graph of the emotional trends within 3 seconds to maintain a responsive interface.
