import { useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';
import AddTask from '../../../components/AddTask';
import SearchBar from '../../../components/SearchBar';
import TaskList from '../../../components/TaskList';
import TaskModal from '../../../components/TaskModal';

import { useDashboard } from '../../../context/DashboardContext';
import type { TaskDTO } from '../../../models/task';

export default function MainContent() {
  const {
    dashboard,
    tasks,
    search,
    setSearch,
    toggleTaskDone,
    deleteTask,
    updateTask,
    addTask,
  } = useDashboard();

  const [openModal, setOpenModal] = useState(false);
  const [editingTask, setEditingTask] = useState<TaskDTO | null>(null);

  function handleNewTask() {
    setEditingTask(null);
    setOpenModal(true);
  }

  function handleEditTask(task: TaskDTO) {
    setEditingTask(task);
    setOpenModal(true);
  }

  if (!dashboard) {
    return <p>Carregando... Esperando a Resposta do Backend</p>;
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
      <AddTask onClick={handleNewTask} />
      <SearchBar value={search} onChange={setSearch} />

      <TaskList
        tasks={tasks}
        onToggleDone={toggleTaskDone}
        onEdit={handleEditTask}
        onDelete={deleteTask}
      />

      <TaskModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        onSaved={() => setOpenModal(false)}
      />
    </>
  );
}
