import React, { useState } from "react";
import {
  Calendar,
  CheckCircle,
  Clock,
  AlertCircle,
  XCircle,
} from "lucide-react";

// Mock status configuration - adjust according to your actual implementation
const getStatusConfig = (status) => {
  const configs = {
    completed: {
      icon: CheckCircle,
      label: "Completed",
      className: "status-completed",
    },
    pending: { icon: Clock, label: "Pending", className: "status-pending" },
    overdue: {
      icon: AlertCircle,
      label: "Overdue",
      className: "status-overdue",
    },
    cancelled: {
      icon: XCircle,
      label: "Cancelled",
      className: "status-cancelled",
    },
  };
  return configs[status] || configs.pending;
};

// Editable Task Viewer Component
function TaskEditor({ task, onSave, onCancel }) {
  const [editedTask, setEditedTask] = useState({ ...task });

  const handleInputChange = (field, value) => {
    setEditedTask((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = () => {
    onSave(editedTask);
  };

  return (
    <div className="task-viewer task-editor">
      {/* Header */}
      <div className="task-header">
        <input
          type="text"
          className="task-title-input"
          value={editedTask.title}
          onChange={(e) => handleInputChange("title", e.target.value)}
          placeholder="Task title..."
        />
        <div className="task-status-select">
          <select
            value={editedTask.status}
            onChange={(e) => handleInputChange("status", e.target.value)}
            className="status-select"
          >
            <option value="pending">Pending</option>
            <option value="completed">Completed</option>
          </select>
        </div>
      </div>

      {/* Description */}
      <div className="task-description-section">
        <textarea
          className="task-description-input"
          value={editedTask.description}
          onChange={(e) => handleInputChange("description", e.target.value)}
          placeholder="Task description..."
          rows={4}
        />
      </div>

      {/* Due Date */}
      <div className="task-due-date">
        <Calendar className="due-date-icon" />
        <span className="due-date-label">Due Date:</span>
        <input
          type="date"
          className="due-date-input"
          value={editedTask.dueDate}
          onChange={(e) => handleInputChange("dueDate", e.target.value)}
        />
        <div className="edit-buttons">
          <button className="save-button" onClick={handleSave}>
            Save
          </button>
          <button className="cancel-button" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default TaskEditor;
