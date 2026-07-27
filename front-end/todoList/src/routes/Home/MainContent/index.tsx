import { useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';
import AddTask from '../../../components/AddTask';
import SearchBar from '../../../components/SearchBar';
import TaskList from '../../../components/TaskList';
import TaskModal from '../../../components/TaskModal';
import ConfirmModal from '../../../components/ConfirmModal';

import { useDashboard } from '../../../context/DashboardContext';
import type { TaskDTO } from '../../../models/task';

import { Trash2 } from 'lucide-react';

import './style.css';

export default function MainContent() {
  const {
    dashboard,
    tasks,
    filters,
    setFilters,
    sidebarOpen,
    toggleTaskDone,
    deleteTask,
    deleteAllTasks,
  } = useDashboard();

  const [openModal, setOpenModal] = useState(false);
  const [openConfirmModal, setOpenConfirmModal] = useState(false);
  const [openDeleteAllModal, setOpenDeleteAllModal] = useState(false);

  const [editingTask, setEditingTask] = useState<TaskDTO | null>(null);
  const [taskSelected, setTaskSelected] = useState<number | null>(null);

  function handleNewTask() {
    setEditingTask(null);
    setOpenModal(true);
  }

  function handleEditTask(task: TaskDTO) {
    setEditingTask(task);
    setOpenModal(true);
  }

  function handleDeleteRequest(id: number) {
    setTaskSelected(id);
    setOpenConfirmModal(true);
  }

  async function handleConfirmDelete() {
    if (taskSelected !== null) {
      await deleteTask(taskSelected);
      setOpenConfirmModal(false);
      setTaskSelected(null);
    }
  }

  async function handleConfirmDeleteAll() {
    await deleteAllTasks();
    setOpenDeleteAllModal(false);
  }

  if (!dashboard) {
    return <p>Carregando...</p>;
  }

  return (
    <main
      className={`main-content ${
        sidebarOpen ? 'sidebar-open' : 'sidebar-closed'
      }`}
    >
      <HeaderContent dashboard={dashboard} />

      <DashboardCards dashboard={dashboard} />

      <AddTask onClick={handleNewTask} />

      <div className="search-container">
        <SearchBar
          value={filters.title ?? ''}
          onChange={(value) =>
            setFilters((previous) => ({
              ...previous,
              title: value,
            }))
          }
        />

        <button
          className="delete-all-button"
          disabled={tasks.length === 0}
          onClick={() => setOpenDeleteAllModal(true)}
        >
          <Trash2 />
        </button>
      </div>

      <TaskList
        tasks={tasks}
        onToggleDone={toggleTaskDone}
        onEdit={handleEditTask}
        onDelete={handleDeleteRequest}
      />

      <TaskModal
        open={openModal}
        taskToEdit={editingTask}
        onClose={() => {
          setOpenModal(false);
          setEditingTask(null);
        }}
      />

      <ConfirmModal
        open={openDeleteAllModal}
        title="Excluir todas as tarefas"
        message="Você tem certeza que deseja excluir todas as tarefas?"
        onConfirm={handleConfirmDeleteAll}
        onCancel={() => setOpenDeleteAllModal(false)}
      />

      <ConfirmModal
        open={openConfirmModal}
        title="Excluir tarefa"
        message="Deseja realmente excluir esta tarefa?"
        onConfirm={handleConfirmDelete}
        onCancel={() => {
          setOpenConfirmModal(false);
          setTaskSelected(null);
        }}
      />
    </main>
  );
}
