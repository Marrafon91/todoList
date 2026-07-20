import { useEffect, useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';
import AddTask from '../../../components/AddTask';
import SearchBar from '../../../components/SearchBar';
import TaskList from '../../../components/TaskList';

import type { DashboardDTO } from '../../../models/dashboard';
import type { TaskDTO } from '../../../models/task';

import { findDashboard } from '../../../services/dashboard-service';
import { findAllTasks } from '../../../services/task-service';
import TaskModal from '../../../components/TaskModal';

export default function MainContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();
  const [tasks, setTasks] = useState<TaskDTO[]>([]);
  const [search, setSearch] = useState('');
  const [openModal, setOpenModal] = useState(false);

  useEffect(() => {
    async function loadDashboard() {
      try {
        const response = await findDashboard();
        setDashboard(response.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadDashboard();
  }, []);

  useEffect(() => {
    async function loadTasks() {
      try {
        const response = await findAllTasks(search);
        setTasks(response.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadTasks();
  }, [search]);

  if (!dashboard) {
    return (
      <p className="error-message">
        Carregando... Esperando resposta do Backend.
      </p>
    );
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />

      <DashboardCards dashboard={dashboard} />

      <AddTask  onClick={() => setOpenModal(true)} />

      <SearchBar value={search} onChange={setSearch} />

      <TaskList tasks={tasks} />
      <TaskModal open={openModal} onClose={() => setOpenModal(false)} />
    </>
  );
}
