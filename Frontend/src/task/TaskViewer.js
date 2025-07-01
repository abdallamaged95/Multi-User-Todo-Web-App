import { Calendar, CheckCircle, Circle } from "lucide-react";
import { useState } from "react";
import TaskEditor from "./TaskEditor";
import "./tasks.css";
import { ApiService } from "../api.js";

function TaskViewer({ task, handleDelete }) {
  const [isEditing, setIsEditing] = useState(false);
  const [taskData, setTaskData] = useState({ ...task });

  const statusConfig = getStatusConfig(taskData.status);
  const StatusIcon = statusConfig.icon;

  const handleSave = async (updatedTask) => {
    setTaskData(updatedTask);
    setIsEditing(false);

    console.log(updatedTask);
    let updatedTaskRes = await ApiService.updateTask(
      updatedTask.id,
      updatedTask
    );
    if (updatedTaskRes.status !== 200) {
      alert("Failed to update task. Please try again later.");
      console.log("Error updating task:", updatedTaskRes);
      return;
    }

    console.log("Task saved:", updatedTask);
  };

  const handleCancel = () => {
    setIsEditing(false);
  };

  // Show editor if in editing mode
  if (isEditing) {
    return (
      <TaskEditor task={taskData} onSave={handleSave} onCancel={handleCancel} />
    );
  }

  // Show regular viewer
  return (
    <div className="task-viewer">
      {/* Header */}
      <div className="task-header">
        <h1 className="task-title">{taskData.title}</h1>
        <div className={`task-status ${statusConfig.className}`}>
          <StatusIcon className="status-icon" />
          <span className="status-label">{statusConfig.label}</span>
        </div>
      </div>

      {/* Description */}
      <div className="task-description-section">
        <p className="task-description">{taskData.description}</p>
      </div>

      {/* Due Date */}
      <div className="task-due-date">
        <Calendar className="due-date-icon" />
        <span className="due-date-label">Due Date:</span>
        <span className="due-date-value">{taskData.dueDate}</span>
        <div className="edit-buttons">
          <button className="update-button" onClick={() => setIsEditing(true)}>
            Update
          </button>
          <button
            className="delete-button"
            onClick={() => handleDelete(taskData.id)}
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  );
}

const getStatusConfig = (status) => {
  const configs = {
    completed: {
      icon: CheckCircle,
      className: "status-completed",
      label: "Completed",
    },
    pending: {
      icon: Circle,
      className: "status-pending",
      label: "Pending",
    },
  };
  return configs[status] || configs["pending"];
};

export default TaskViewer;
