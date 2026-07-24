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

export default function MainContent() {
  const { dashboard, tasks, filters, setFilters, toggleTaskDone, deleteTask } =
    useDashboard();

  const [openModal, setOpenModal] = useState(false);
  const [openConfirmModal, setOpenConfirmModal] = useState(false);

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
    console.log("Clique para excluir:", id);
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

  if (!dashboard) {
    return <p>Carregando... Esperando a Resposta do Backend</p>;
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
      <AddTask onClick={handleNewTask} />

      <SearchBar
        value={filters.title ?? ''}
        onChange={(value) =>
          setFilters((previous: any) => ({
            ...previous,
            title: value,
          }))
        }
      />

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
