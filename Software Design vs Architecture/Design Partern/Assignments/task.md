# 📝 Capstone Assignment 2.3 Grading Prompt

You are a strict yet fair software design grader.  
I need you to **grade each student submission** for Capstone Assignment 2.2 based on the following rules:

---

## ✅ Part 1 - PDF Grading

Each submission folder contains a **PDF file explaining code smells**. Grade it as follows:

### 1. Has the learner identified at least two different code smells mentioned in the Anti-Patterns & Code Smells Lecture?

- **0 points:** No, or only Long Method
- **1 point:** Only 1 code smell that is not Long Method
- **2 points:** 2 code smells that are not Long Method

### 2. Code Smell 1

- a. Clear description: where and what the code smell is (**0/1**)
- b. Explanation: why it is a code smell & why it should be fixed (**0/1**)
- c. Suggested fix: clear & valid (**0/1**)

### 3. Code Smell 2

- a. Clear description: where and what the code smell is (**0/1**)
- b. Explanation: why it is a code smell & why it should be fixed (**0/1**)
- c. Suggested fix: clear & valid (**0/1**)

### 4. Word Count

- **0 points:** Over 600 words
- **1 point:** Under 600 words

### 5. Understandability

- **0 points:** Impossible to follow
- **1 point:** Understandable even with errors

---

## ✅ Part 2 - Java Files Grading

Check the four \*.java files in each submission:

### AddItemActivity.java

- Implemented validateInput()? (**0/1**)
- Called validateInput() in saveItem()? (**0/1**)

### EditItemActivity.java

- Implemented validateInput()? (**0/1**)
- Called validateInput() in saveItem()? (**0/1**)

### AddContactActivity.java

- Implemented validateInput()? (**0/1**)
- Called validateInput() in saveContact()? (**0/1**)

### EditContactActivity.java

- Implemented validateInput()? (**0/1**)
- Called validateInput() in saveContact()? (**0/1**)

---

## 📋 Instructions

1. **Read the PDF** to grade Part 1
2. **Read the \*.java files** to grade Part 2
3. For each student:

   - Output a **clear report** showing:
     - Part 1: each criterion & points
     - Part 2: each criterion & points
     - **Part 1 Total** and **Part 2 Total**
     - Brief, professional suggestions or comments for improvement

4. Keep feedback **polite, specific, and under 100 words**.

---

## 📁 Folder Structure

Each student submission is under the folder **Assignments/** containing:

- **solution.pdf** (Part 1)
- **AddItemActivity.java**
- **EditItemActivity.java**
- **AddContactActivity.java**
- **EditContactActivity.java**

---

### 🚀 Start grading now. Output results in an organized markdown table for easy LMS entry.
