## Reeve - User Guide

* Table of Contents
{:toc}

## 1. Introduction
**Welcome to Reeve!** 

### 1.1 About Reeve
Are you looking for a one-stop application that can handle all your private tutoring needs? Then you are in luck!

**Reeve** is a desktop application for **private tutors to to better manage both administrative and academic details of their students**, optimised for use via a
**Command Line Interface (CLI)** for receiving inputs while still having the benefits of a **Graphical User Interface (GUI)** for displaying information. In addition, **Reeve** comes with a customisable personal scheduler to assist you keeping track of your classes. **Reeve** also allows you to set timely reminders for yourself. 

Get to experience all the above without even having to move your mouse at all! 
 
Also, did we mention that you are able to achieve all the above **without internet access at all**?

If you are a private tutor that wants to not only manage your students' administrative details with ease but to also better meet their needs, then **Reeve** is made for you!

Let's dive into the User Guide to find out more!

### 1.2 Understanding the User Guide
The goal of this User Guide is to provide you with the information on how to utilise **Reeve** to its fullest. 

For those who are unfamiliar with what a CLI is, no worries! This User Guide will ensure that you will be able to understand how to use a CLI by the end of it! Rest assured!

Here is a summary of the symbols that are used in this User Guide:

Symbol | Meaning 
-------|-------- 
`command` | A grey highlight indicates a command that can be executed by Reeve.
:information_source: | Indicates important information. 

## 2. Quick start

This section serves to explain how to set up **Reeve** on your computer and how to make sense of the visual layout of the application. 

### 2.1 Setting Up Reeve
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `jar` file from [here](https://github.com/AY2021S1-CS2103T-W15-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Reeve.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all students.

   * **`add`**`add n/Alex p/93211234 s/Commonwealth Secondary School y/Primary 6 v/Blk 33 West Coast Rd #21-214
   t/1 1430-1630 f/25 d/12/12/2020` : Adds a student named `Alex` to Reeve.

   * **`delete`**`3` : Deletes the 3rd student shown in the current list.

   * **`clear`** : Deletes all students.

   * **`exit`** : Exits Reeve.

1. Refer to the [Features](#features) section below for details of each command.

### 2.2 Making sense of Reeve's layout
(to be added when GUI is finalised)

## 3. Features

This section serves to provide you a detailed explanation of how the various features of **Reeve** work and how to use these features.

### 3.1 Understanding the command format

The following points explain how to make sense of the command format:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [a/DETAIL_TEXT]` can be used as `n/John Doe a/Likes to read books` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[a/DETAIL_TEXT]…​` can be used as ` ` (i.e. 0 times), `a/Likes to read books`, `a/Likes sweets a/Needs help with Algebra` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

### 3.2 Student Administrative Features

Reeve's student administrative features allows you to keep track of key administrative details of each of your students such as phone number, class venue, tuition fee, etc. 
Thereafter, you will be able to view, edit find or delete these students.

#### 3.2.1 Adding a student: `add` (By: Alex and Hogan)

You can add a student together with his/her individual administrative details into **Reeve's** student list.

Format: `add n/NAME p/PHONE s/SCHOOL y/YEAR v/CLASS_VENUE t/CLASS_TIME f/FEE d/LAST_PAYMENT_DATE [a/ADDITIONAL_DETAILS]`

<div markdown="block" class="alert alert-info">

:information_source: The format of TIME is as follows:
* {int: day_of_week} {int: start_time}-{int: end_time}.
* day_of_week is any number from 1 to 7, where 1 refers to Monday while 7 refers to Sunday.
* start_time and end_time follows the 24-hr clock format (e.g. 1pm refers to 1300).

:information_source: The format of LAST_PAYMENT_DATE is as follows:
* d/m/yyyy or dd/mm/yyyy (e.g. both 03/02/2020 and 3/2/2020 are acceptable).

:information_source: The format of YEAR is as follows:
* TYPE_OF_SCHOOL LEVEL (e.g. y/primary 2 and y/p 2 are the same and both acceptable).
* TYPE_OF_SCHOOL can be primary(pri, p), secondary(sec, s) or jc. 
* LEVEL has to correspond with the TYPE_OF_SCHOOL (e.g. primary 1 - primary 6, secondary 1 - secondary 5, jc 1 - jc 2)
<br>

</div>

Examples:
* `add n/Alex p/93211234 s/Commonwealth Secondary School y/pri 6 v/Blk 33 West Coast Rd #21-214
t/1 1430-1630 f/25 d/12/12/2020`
* `add n/John Doe p/98765432 s/Woodlands Secondary School y/s 2 v/347 Woodlands Ave 3, Singapore 730347
t/1 1200-1400 f/30 d/24/09/2020 a/Likes chocolates a/Needs help with Algebra`

#### 3.2.2 Editing a student : `edit` (By: Vaishak)

Edits an existing student in Reeve.

Format: `edit INDEX [n/NAME] [p/PHONE] [s/SCHOOL] [y/YEAR] [v/CLASS_VENUE] [t/CLASS_TIME] [f/FEE] [d/PAYMENT_DATE] `

<div markdown="block" class="alert alert-info">

:information_source: The format of TIME is {int: day_of_week} {int: start_time}-{int: end_time}<br>

</div>

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Start time has to be before end time.

Examples:
*  `edit 1 n/Alex p/99999999 s/Meridian Junior College` Edits the name, phone number and school of the 1st student to be `Alex`, `99999999` and `Meridian Junior College` respectively.
*  `edit 3 sb/Mathematics v/Blk 33 West Coast Rd #21-214 t/1 1430-1630` Edits the subject, venue and time of the third student to be `Mathematics`, `Blk 33 West Coast Rd #21-214` and `1 1430-1630` respectively.

#### 3.2.3 Locating students: `find` (By: Choon Siong)

Finds students who satisfy the given search criteria.

Format: `find [n/NAME] [s/SCHOOL] [y/YEAR]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* At least one of the optional fields must be provided.
* The order of the optional fields do not matter. e.g `n/Hans s/River Valley` will match `s/River Valley n/Hans`
* Only full words will be matched. e.g `han` will not match `hans`.
* For the name, students with a name that matches any whole keyword specified for the name will be considered to match for the name.
* For the school, students with a school that contains any keyword specified for the school will be considered to match for the school.
* For the year, students with a year that contains any keywords specified for the year will be considered to match for the year.
* Only students matching all criteria specified will be returned (i.e `AND` search).

Examples:
* `find n/Alex david` matches `Alex David`, `alex david` and `Alex david`.
* `find n/Alex david` does not match `Alexis Davinder`.
* `find s/yishun sec` matches `Yishun Secondary School` and `Yishun Town Secondary School`.
* `find s/yishun secondary` does not match `Yishun Sec`
* `find y/sec 3` matches `sec 3`, `Secondary 3`
* `find y/sec 3` matches `sec 4`
* `find n/alex s/yishun y/sec 3` searches for all students who match all of `n/alex`, `s/yishun` and `y/sec 3`.

#### 3.2.4 Listing all students : `list`

Shows the list of all students in Reeve.

:information source: You will need to use this if you want to view the full student list after using commands such as `find` and `schedule`.

Format: `list`

#### 3.2.5 Deleting a student : `delete`

Deletes the specified student from Reeve.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in Reeve.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

#### 3.2.6 Finding students with overdue fees: `overdue` (By: Ying Gao)

Finds students whose date of last payment is more than a month ago.

Format: `unpaid`

#### 3.2.7 Managing additional details for a student: `detail` (By: Vaishak) 

Adds, edits or deletes an additional detail for a specified student.

Format: `detail [add] [edit] [delete] STUDENT_INDEX [i/DETAIL_INDEX] [d/DETAIL_TEXT]`

* Exactly one of the following fields must be present: `[add]`, `[edit]` or `[delete]`
* The student index and detail index **must be positive integers** 1, 2, 3, …​
* `detail add` adds the given additional detail to the student at the specified `STUDENT_INDEX`.
* `detail add` requires the following optional field: `[d/DETAIL_TEXT]`.
* `detail edit` edits the additional detail at the specified `DETAIL_INDEX`, for the student at the specified `STUDENT_INDEX`
* `detail edit` requires the following optional fields: `[i/DETAIL_INDEX] [d/DETAIL_TEXT]`.
* `detail delete` deletes the additional detail at the specified `DETAIL_INDEX`, for the student at the specified `STUDENT_INDEX`
* `detail delete` requires the following optional field: `[i/DETAIL_INDEX]`.

Examples:  
* `detail add 1 d/Smart` adds the "Smart" detail to the 1st student in Reeve.
* `detail edit 1 i/2 d/Handsome` edits the 2nd detail for the 1st student in Reeve, to "Handsome".
* `detail delete 1 i/3` deletes the 3rd detail for the 1st student in Reeve.

#### 3.2.8 Clearing all entries : `clear`

Clears all student data from Reeve.

Format: `clear`

### 3.3 Student Academics Features

Reeve's student academics features allows you to keep track of key academic details of each of your students such as questions, exams and etc. 
Thereafter, you will be able to view, edit or delete these details of each student. 

#### 3.3.1 Recording questions from a student: `question` (By: Ying Gao) 

Adds, resolves or remove questions from a specified student.

General Format: `question COMMAND_WORD INDEX DETAILS`

* The `COMMAND_WORD` field accepts either `add`, `solve` or `delete`.
* The command affects the student at the specified `INDEX`.
* The index **must be a positive integer** 1, 2, 3, …​
* The format of `DETAILS` varies with each command word as explained below.

#### 3.3.1.2 Adding a question: `add`

Adds a new question to the student.

Format: `question add INDEX t/QUESTION`

* This records a new unresolved question to a student.
* `QUESTION` must not be empty.

Example:
* `question add 1 t/How do birds fly?` records "How do birds fly?" as a question from the 1st student in Reeve.

#### 3.3.1.3 Resolving a question: `solve`

Marks a student's question as resolved.

Format: `question solve INDEX i/QUESTION_INDEX t/SOLUTION`

* This resolves the question at the specified `QUESTION_INDEX`.
* `QUESTION_INDEX` **must be a positive integer** 1, 2, 3, …​
* `SOLUTION` must not be empty.

Example:
* `question solve 1 i/1 t/Read a book.` marks the 1st question of the 1st student in Reeve as answered.

#### 3.3.1.4 Deleting a question: `delete`

Deletes a student's question.

Format: `question delete INDEX i/QUESTION_INDEX`

* This deletes the question at the specified `QUESTION_INDEX`.
* `QUESTION_INDEX` **must be a positive integer** 1, 2, 3, …​

Example:
* `question delete 1 i/1` deletes the 1st question of the 1st student in Reeve.

#### 3.3.2 Recording exams of a student: `exam` (By: Hogan)

You can add or delete an exam to/from a specified student.

General Format: `exam COMMAND_WORD_EXAM INDEX PARAMETERS`

* The `COMMAND_WORD_EXAM` field accepts either `add` or `delete`.
* The command affects the student at the specified `INDEX`.
* The index **must be a positive integer** 1, 2, 3, …​
* The format of `PARAMETERS` varies with each command word as explained below.

#### 3.3.2.1 Adding an exam record to a student: `exam add`

You can add an exam record to a specified student in Reeve.

Format: `exam add INDEX n/EXAM_NAME d/EXAM_DATE s/EXAM_SCORE`

* Adds the given exam to the student at the specified `INDEX`.

:information_source: The format of EXAM_DATE is as follows:
* dd/mm/yyyy or d/m/yyyy (e.g. 08/12/2020).

:information_source: The format of EXAM_SCORE is as follows:
* x/y where x and y are non-negative integers. 
* x has to be less than or equal to y (e.g. 30/50).

Examples:
* `exam add 1 n/Mid Year 2020 d/08/12/2020 s/40/60` adds the "Mid Year 2020" exam  with date 8 Dec 2020 and 
score 40/60 to the first student in Reeve.

* `exam add 5 n/End of Year 2020 d/12/05/2020 s/67/100` adds the "End of Year 2020" exam  with date 12 May 2020 and 
score 67/100 to the fifth student in Reeve.

#### 3.3.2.2 Deleting an exam record to a student: `exam delete`

You can delete a specific exam from a specified student in Reeve.

Format: `exam delete STUDENT_INDEX i/EXAM_INDEX`

* Deletes the an exam at `EXAM_INDEX` in the specified student's exam list.
* The specified student is chosen based on `STUDENT_INDEX` of Reeve. 
* The `STUDENT_INDEX` refers to the index number shown in the displayed students list.

Examples:
* `exam delete 1 i/1` deletes the first exam from the first student in the displayed students list.
* `exam delete 2 i/5` deletes the fifth exam from the second student in the displayed students list.

### 3.4 Miscellaneous Features

#### 3.4.1 Listing lessons schedule on a particular date: `schedule` (By: Alex)

List the students that the user has class with on the given date.

Format: `schedule DATE`

* Date must be in the format of **dd/mm/yyyy**.

Examples:
* `schedule 20/11/2020` outputs a list of students who has lessons with the user on that date

#### 3.4.2 Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


#### 3.4.3 Exiting the program : `exit`

Exits the program.

Format: `exit`

## 4. Command summary

This section provides a summary of all the commands in **Reeve**.

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE s/SCHOOL y/YEAR v/CLASS_VENUE t/CLASS_TIME f/FEE d/LAST_PAYMENT_DATE [a/ADDITIONAL_DETAILS]​` <br> e.g., `add n/John Doe p/98765432 s/Woodlands Secondary School y/Secondary 2 v/347 Woodlands Ave 3, Singapore 730347 t/1 1200-1400 f/30 d/24/09/2020 a/Likes chocolates a/Needs help with Algebra`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g. `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [n/NAME] [p/PHONE] [v/CLASS_VENUE] [s/SCHOOL] [sb/SUBJECT] [y/YEAR] [t/CLASS_TIME] [a/ADDITIONAL_DETAILS]`<br> e.g.,`edit 1 n/Alex p/99999999 s/Meridian Junior College`
**Find** | `find [n/NAME] [s/SCHOOL] [y/YEAR] [sb/SUBJECT]`<br> e.g., `find n/alex s/yishun`
**List** | `list`
**Help** | `help`
**Add exam** | `exam add INDEX n/EXAM_NAME d/EXAM_DATE s/EXAM_SCORE` <br> e.g. `exam add 1 n/Mid Year 2020 d/08/12/2020 s/40/60`
**Delete exam** | `exam delete STUDENT_INDEX i/EXAM_INDEX` <br> e.g. `exam delete 2 i/5`

## 5. Glossary

The following table provides the definitions of the various terms used in this User Guide.
Term | Definition
--------|------------------

## 6. FAQ
This section provides the answers to Frequently Asked Questions(FAQ) by users.

1. How do I transfer my data to another Computer?<br>
Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Reeve home folder.

2. Do I have to manually save my data?<br>
Reeve data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
