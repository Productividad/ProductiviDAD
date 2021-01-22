BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "incomeExpenses" (
	"ID_incomeExpenses"	INTEGER NOT NULL UNIQUE,
	"amount"	REAL NOT NULL,
	"concept"	TEXT NOT NULL,
	"income_expense"	INTEGER NOT NULL,
	"FK_ID_page"	INTEGER NOT NULL,
	PRIMARY KEY("ID_incomeExpenses" AUTOINCREMENT),
	FOREIGN KEY("FK_ID_page") REFERENCES "page"("ID_page")
);
CREATE TABLE IF NOT EXISTS "projects" (
	"ID_project"	INTEGER NOT NULL UNIQUE,
	"title_project"	TEXT NOT NULL,
	"progress"	NUMERIC,
	"description_project"	TEXT,
	"completed_project"	INTEGER NOT NULL,
	"deadline_project"	TEXT,
	PRIMARY KEY("ID_project" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "tasks" (
	"ID_task"	INTEGER NOT NULL UNIQUE,
	"title_task"	TEXT NOT NULL,
	"completed"	INTEGER NOT NULL,
	"description_task"	TEXT,
	"color_task"	TEXT,
	"deadline_task"	NUMERIC,
	"FK_ID_Page"	INTEGER NOT NULL,
	"FK_ID_Parent_task"	INTEGER,
	"FK_ID_project"	INTEGER,
	PRIMARY KEY("ID_task" AUTOINCREMENT),
	FOREIGN KEY("FK_ID_Parent_task") REFERENCES "tasks"("ID_task"),
	FOREIGN KEY("FK_ID_Page") REFERENCES "page"("ID_page"),
	FOREIGN KEY("FK_ID_project") REFERENCES "projects"("ID_project")
);
CREATE TABLE IF NOT EXISTS "notes" (
	"ID_notes"	INTEGER NOT NULL UNIQUE,
	"title_note"	TEXT,
	"content_note"	TEXT NOT NULL,
	"FK_ID_page"	INTEGER NOT NULL,
	PRIMARY KEY("ID_notes" AUTOINCREMENT),
	FOREIGN KEY("FK_ID_page") REFERENCES "page"("ID_page")
);
CREATE TABLE IF NOT EXISTS "pomodoro" (
	"ID_pomodoro"	INTEGER NOT NULL UNIQUE,
	"title_pomodoro"	TEXT NOT NULL,
	"time_spent"	NUMERIC NOT NULL,
	"FK_ID_page"	INTEGER NOT NULL,
	"FK_ID_task"	INTEGER,
	PRIMARY KEY("ID_pomodoro" AUTOINCREMENT),
	FOREIGN KEY("FK_ID_task") REFERENCES "tasks"("ID_task"),
	FOREIGN KEY("FK_ID_page") REFERENCES "page"("ID_page")
);
CREATE TABLE IF NOT EXISTS "page" (
	"ID_page"	INTEGER NOT NULL UNIQUE,
	"date_page"	TEXT NOT NULL UNIQUE,
	PRIMARY KEY("ID_page" AUTOINCREMENT)
);
COMMIT;
