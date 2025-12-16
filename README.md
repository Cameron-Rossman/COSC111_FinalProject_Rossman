Fantasy Draft Manager

This project is a command line fantasy football draft manager designed for live, in person drafts. During live drafts, most people still rely on pen and paper, crossing names off lists, or switching between web pages while trying to track available players and their own team. This program replaces that manual process with a simple digital system that keeps everything organized in one place.

The program loads player data from CSV files and tracks all undrafted players, drafted players, and position specific lists including quarterbacks, running backs, wide receivers, tight ends, and kickers. Users can view the best available players overall or by position, draft a player, cross a player off, and view their drafted team. Rankings update dynamically as players are removed so the user always sees the best remaining options. The interface is command line based to match the scope of the course and the programming concepts taught.

From a technical standpoint, the project uses object oriented design with a Player class to represent player data and a DraftManager class to handle draft logic, user commands, and data flow. Arrays are used to store players since arrays were a major focus of the course. Input validation and error handling are included to prevent crashes from invalid commands or missing data. Output is formatted using fixed width formatting so columns remain aligned and readable.

Commands available in the program include show all, show qb, show rb, show wr, show te, show k, draft followed by a player id, cross followed by a player id, show team, help, and exit.

This project focuses on improving the live fantasy draft experience by replacing pen and paper tracking with a structured digital workflow while staying within the limits of what was taught in the course.