import { useEffect, useState } from "react";
import ReactDOM from "react-dom/client";
import TaskViewer from "./task/TaskViewer.js";
import "./index.css";
import TaskEditor from "./task/TaskEditor.js";
import Login from "./login/Login.js";
import { ApiService } from "./api.js";

function App() {
  const [taskList, setTaskList] = useState(taskData);
  const [isCreatingTask, setIsCreatingTask] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loggedUser, setLoggedUser] = useState("");

  const deleteTask = async (taskId) => {
    let deletedTaskRes = await ApiService.deleteTask(taskId);
    if (deletedTaskRes.status !== 200) {
      alert("Failed to delete task. Please try again later.");
      console.log("Error deleting task:", deletedTaskRes);
      return;
    }

    setTaskList((prevTasks) => prevTasks.filter((task) => task.id !== taskId));
    console.log(taskList);
  };

  const handleNewTaskCreated = async (newTask) => {
    let createTaskRes = await ApiService.createTask(newTask);
    if (createTaskRes.status !== 200) {
      alert("Failed to create task. Please try again later.");
      setIsCreatingTask(false);
      return;
    }

    console.log("Task created successfully:", createTaskRes.data);
    newTask.id = createTaskRes.data.id;
    setTaskList((prevTasks) => [...taskList, newTask]);
    setIsCreatingTask(false);
  };

  const handelNewTaskCanceled = () => {
    setIsCreatingTask(false);
  };

  const handleLoggedIn = async (response, username) => {
    setIsLoggedIn(true);
    setIsLoading(true);
    setLoggedUser(username);
    console.log("User logged in:", loggedUser);

    let tasksDataRes = await ApiService.getTasks();
    if (tasksDataRes.status !== 200) {
      alert("Failed to retrieve tasks. Please try again later.");
      setIsLoading(false);
      return;
    }

    console.log(tasksDataRes);
    setTaskList(tasksDataRes.data);
    setIsLoading(false);
  };

  if (!isLoggedIn) {
    return (
      <>
        <Login onLoggedIn={handleLoggedIn} />
      </>
    );
  }

  if (isLoading) return <h1 className="loader">Loading...</h1>;

  return (
    <div>
      <Header username={loggedUser} />

      <ul className="task-list content-section">
        {taskList.map((taskItem) => (
          <TaskViewer
            task={taskItem}
            handleDelete={deleteTask}
            key={taskItem.id}
          />
        ))}
        {isCreatingTask && (
          <TaskEditor
            task={defaultTask}
            onSave={handleNewTaskCreated}
            onCancel={handelNewTaskCanceled}
            key={defaultTask.id}
          />
        )}
        {!isCreatingTask && (
          <button
            className="create-button"
            onClick={() => setIsCreatingTask(true)}
          >
            Create Task
          </button>
        )}
      </ul>
    </div>
  );
}

const Header = ({ username }) => {
  return (
    <header className="header">
      <h1 className="header-welcom">Welcom Back {username}</h1>
      <p className="header-subtitle">Manage your tasks efficiently</p>
    </header>
  );
};

const defaultTask = {
  id: 0,
  title: "New Task",
  description: "Task description goes here...",
  dueDate: "2025-01-01",
  status: "pending",
};

const taskData = [];

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<App />);
