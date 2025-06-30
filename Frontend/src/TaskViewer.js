import {
  Calendar,
  Clock,
  CheckCircle,
  AlertCircle,
  Circle,
} from "lucide-react";
import { useState } from "react";
import TaskEditor from "./TaskEditor";

function TaskViewer({ ...task }) {
  const [isEditing, setIsEditing] = useState(false);
  const [taskData, setTaskData] = useState(task);

  const statusConfig = getStatusConfig(taskData.status);
  const StatusIcon = statusConfig.icon;

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = (updatedTask) => {
    setTaskData(updatedTask);
    setIsEditing(false);
    // Here you would typically also save to your backend/state management
    console.log("Saving task:", updatedTask);
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
        <button className="update-button" onClick={handleEdit}>
          Update
        </button>
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

function updateButtonHandler(click, setClick) {
  setClick(click + 1);
  alert("Update button clicked " + click + " Times");
}

export default TaskViewer;
