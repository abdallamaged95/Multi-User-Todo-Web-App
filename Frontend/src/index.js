import React from "react";
import ReactDOM from "react-dom/client";
import TaskViewer from "./TaskViewer";
import "./index.css";

function App() {
  return (
    <div>
      <h1>hello User</h1>
      <ul className="task-list">
        {taskData.map((task) => (
          <TaskViewer {...task} />
        ))}
      </ul>
      {/* <TaskViewer {...taskData[0]} /> */}
    </div>
  );
}

const taskData = [
  {
    title: "Update API Documentation",
    description:
      "Update the REST API documentation to reflect recent changes in endpoints and authentication methods. Include code examples and error handling scenarios.",
    dueDate: "2025-06-20",
    status: "overdue",
  },
  {
    title: "Security Audit Review",
    description:
      "Review the quarterly security audit report and address any critical vulnerabilities identified. Coordinate with the IT security team for remediation planning.",
    dueDate: "2025-08-15",
    status: "pending",
  },
  {
    title: "Database Migration",
    description:
      "Migrate user data from legacy MySQL database to new PostgreSQL cluster. Ensure zero downtime during migration and verify data integrity post-migration.",
    dueDate: "2025-06-25",
    status: "completed",
  },
];

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
