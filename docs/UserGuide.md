## User Guide

**Welcome to Reeve!**

Reeve is a desktop app for **private tutors to manage the details of their students**, optimised for use via a 
**Command Line Interface (CLI)** for receiving inputs while still having the benefits of a **Graphical User Interface (GUI)** for displaying information.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `jar` file from [here](https://github.com/AY2021S1-CS2103T-W15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Reeve.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`add n/Alex p/93211234 s/Commonwealth Secondary School y/Secondary 2 v/Blk 33 West Coast Rd #21-214 
   t/1 1430-1630 a/Alex is quiet m/www.zoom123.com sb/Mathematics` : Adds a student named `Alex` to Reeve.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a student: `add`

Adds a student to Reeve. (written by: Alex and Hogan)

Format: `add n/NAME p/PHONE s/SCHOOL y/YEAR v/VENUE t/TIME 
[a/ADDITIONAL_DETAILS] [m/MEETING_LINK] [sb/SUBJECT]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
The format of TIME is {int: day_of_week} {int: start_time}-{int: end_time}
</div>

Example:
* `add n/Alex p/93211234 s/Commonwealth Secondary School y/Secondary 2 v/Blk 33 West Coast Rd #21-214 t/1 1430-1630 `
* `add n/John p/98765432 s/Newton Primary School y/Primary 5 v/Blk 123 East Coast Rd #02-345 t/3 1600-1830 
a/John likes chocolate m/www.zoom987.com sb/English`

### Listing all students : `list`

Shows a list of all students in Reeve.

Format: `list`

### Editing a student : `edit`

Edits an existing student in Reeve (Written by: Vaishak).

Format: `edit INDEX [n/NAME] [p/PHONE] [s/SCHOOL] [y/YEAR] [v/VENUE] [t/TIME] [a/ADDITIONAL_DETAILS] [m/MEETING_LINK] [sb/SUBJECT]`

<div markdown="span" class="alert alert-primary">:information_source: **Note:**
The format of TIME is {int: day_of_week} {int: start_time}-{int: end_time}
</div>


* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 n/Alex p/99999999 s/Meridian Junior College` Edits the name, phone number and school of the 1st student to be `Alex`, `99999999` and `Meridian Junior College` respectively.
*  `edit 3 sb/Mathematics v/Blk 33 West Coast Rd #21-214 t/1 1430-1630` Edits the subject, venue and time of the third student to be `Mathematics`, `Blk 33 West Coast Rd #21-214` and `1 1430-1630` respectively.

### Locating students: `find`

(written by: Ying Gao)
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

(written by: Choon Siong)
Examples:
* `find n/Alex david` matches `Alex David`, `alex david` and `Alex david`.
* `find n/Alex david` does not match `Alexis Davinder`.
* `find s/yishun sec` matches `Yishun Secondary School` and `Yishun Town Secondary School`.
* `find s/yishun secondary` does not match `Yishun Sec`
* `find y/sec 3` matches `sec 3`, `Secondary 3`
* `find y/sec 3` matches `sec 4`
* `find n/alex s/yishun y/sec 3` searches for all students who match all of `n/alex`, `s/yishun` and `y/sec 3`.
* 

### Recording questions from a student: `question`

(written by: Ying Gao)
Adds, resolves or remove questions from a specified student.

Format: `question INDEX [a/QUESTION_ADD] [s/QUESTION_INDEX] [d/QUESTION_INDEX]`

* Exactly one of the optional fields must be present.
* The index and question index **must be positive integers** 1, 2, 3, …​
* The `a/` prefix adds a new unanswered question to the student at the specified `INDEX`.
* The `s/` prefix marks an unanswered question, of the student at the specified `INDEX`, at the specified 
`QUESTION_INDEX` as solved.
* The `d/` prefix deletes the question at the specified `QUESTION_INDEX` from the student at the specified `INDEX`.

Examples:
* `question 1 a/How do birds fly?` records "How do birds fly?" as a new question from the 1st student in Reeve.
* `question 2 s/3` marks the 3rd question of the 2nd student in Reeve as answered.
* `question 1 d/2` removes the 2nd question from the 1st student in Reeve.

### Deleting a student : `delete`

Deletes the specified student from Reeve.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed students list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in Reeve.
* `find n/Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from Reeve.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Reeve data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Reeve home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE s/SCHOOL y/YEAR v/CLASS_VENUE t/CLASS_TIME [a/ADDITIONAL_DETAILS] [m/MEETING_LINK] [sb/SUBJECT]​` <br> e.g., `add n/John p/98765432 s/Newton Primary School y/Primary 5 v/Blk 123 East Coast Rd #02-345 t/3 1600-1830 a/John likes chocolate m/www.zoom987.com sb/English`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [n/NAME] [p/PHONE] [v/CLASS_VENUE] [s/SCHOOL] [sb/SUBJECT] [y/YEAR] [t/CLASS_TIME] [a/ADDITIONAL_DETAILS]`<br> e.g.,`edit 1 n/Alex p/99999999 s/Meridian Junior College`
**Find** | `find [n/NAME] [s/SCHOOL] [y/YEAR] [sb/SUBJECT]`<br> e.g., `find n/alex s/yishun`
**List** | `list`
**Help** | `help`
