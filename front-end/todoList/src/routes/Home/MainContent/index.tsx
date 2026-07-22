import { useCallback, useEffect, useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';
import AddTask from '../../../components/AddTask';
import SearchBar from '../../../components/SearchBar';
import TaskList from '../../../components/TaskList';
import TaskModal from '../../../components/TaskModal';

import type { DashboardDTO } from '../../../models/dashboard';
import type { TaskDTO } from '../../../models/task';

import { findDashboard } from '../../../services/dashboard-service';
import { findAllTasks } from '../../../services/task-service';

export default function MainContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();
  const [tasks, setTasks] = useState<TaskDTO[]>([]);
  const [search, setSearch] = useState('');
  const [openModal, setOpenModal] = useState(false);

  const loadDashboard = useCallback(async () => {
    try {
      const response = await findDashboard();

      setDashboard(response.data);
    } catch (error) {
      console.log(error);
    }
  }, []);

  const loadTasks = useCallback(async () => {
    try {
      const response = await findAllTasks(search);
      setTasks(response.data);
    } catch (error) {
      console.log(error);
    }
  }, [search]);

  useEffect(() => {
    loadDashboard();
  }, [loadDashboard]);

  useEffect(() => {
    loadTasks();
  }, [loadTasks]);

  function handleTaskCreated(task: TaskDTO) {
    setTasks((previous) => [task, ...previous]);

    loadDashboard();
  }

  if (!dashboard) {
    return <p>Carregando...</p>;
  }

  async function handleToggleDone(task: TaskDTO) {
    try {
      const response = await toggleDone(task.id);

      setTasks((previous) =>
        previous.map((item) => (item.id === task.id ? response.data : item)),
      );

      loadDashboard();
    } catch (error) {
      console.log(error);
    }
  }

  function handleEditTask(task: TaskDTO) {
    console.log('Editar:', task);
  }

  function handleDeleteTask(id: number) {
    console.log('Excluir:', id);
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
      <AddTask onClick={() => setOpenModal(true)} />
      <SearchBar value={search} onChange={setSearch} />

      <TaskList
        tasks={tasks}
        onToggleDone={handleToggleDone}
        onEdit={handleEditTask}
        onDelete={handleDeleteTask}
      />

      <TaskModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        onSaved={handleTaskCreated}
      />
    </>
  );
}
