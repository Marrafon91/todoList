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

export default function MainContent() {
  const {
    dashboard,
    tasks,
    filters,
    setFilters,
    toggleTaskDone,
    deleteTask,
    deleteAllTasks,
  } = useDashboard();

  const [openModal, setOpenModal] = useState(false);
  const [openConfirmModal, setOpenConfirmModal] = useState(false);

  const [editingTask, setEditingTask] = useState<TaskDTO | null>(null);
  const [taskSelected, setTaskSelected] = useState<number | null>(null);
  const [openDeleteAllModal, setOpenDeleteAllModal] = useState(false);

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
    try {
      await deleteAllTasks();

      setOpenDeleteAllModal(false);
    } catch (error) {
      console.log(error);
    }
  }

  if (!dashboard) {
    return <p>Carregando... Esperando a Resposta do Backend</p>;
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
      <AddTask onClick={handleNewTask} />

      <div className="search-container">
        <SearchBar
          value={filters.title ?? ''}
          onChange={(value) =>
            setFilters((previous: any) => ({
              ...previous,
              title: value,
            }))
          }
        />

        <button
          className="delete-all-button"
          onClick={() => setOpenDeleteAllModal(true)}
        >
          <Trash2 size={22} />
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
        message="Você tem certeza que deseja excluir todas as tarefas?"
        onConfirm={handleConfirmDeleteAll}
        onCancel={() => setOpenDeleteAllModal(false)}
      />

      <ConfirmModal
        open={openConfirmModal}
        message="Deseja realmente excluir esta tarefa?"
        onConfirm={handleConfirmDelete}
        onCancel={() => {
          setOpenConfirmModal(false);
          setTaskSelected(null);
        }}
      />
    </>
  );
}
