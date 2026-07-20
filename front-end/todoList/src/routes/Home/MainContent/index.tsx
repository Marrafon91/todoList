import { useEffect, useState } from 'react';

import HeaderContent from '../../../components/HeaderContent';
import DashboardCards from '../../../components/DashboardCards';

import type { DashboardDTO } from '../../../models/dashboard';
import type { TaskDTO } from '../../../models/task';

import { findDashboard } from '../../../services/dashboard-service';
import { findAllTasks } from '../../../services/task-service';
import AddTask from '../../../components/AddTask';
import SearchBar from '../../../components/SearchBar';
import TaskList from '../../../components/TaskList';

export default function MainContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();
  const [tasks, setTasks] = useState<TaskDTO[]>([]);

  useEffect(() => {
    async function loadData() {
      try {
        const [dashboardResponse, taskResponse] = await Promise.all([
          findDashboard(),
          findAllTasks(),
        ]);

        setDashboard(dashboardResponse.data);
        setTasks(taskResponse.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadData();
  }, []);

  if (!dashboard) {
    return (
      <p className="error-message">
        Carregando... Espedando resposta do Backend.
      </p>
    );
  }

  return (
    <>
      <HeaderContent dashboard={dashboard} />
      <DashboardCards dashboard={dashboard} />
      <AddTask />
      <SearchBar />
      <TaskList tasks={tasks} />
    </>
  );
}
