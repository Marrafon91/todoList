import { useEffect, useState } from 'react';
import './style.css';
import type { DashboardDTO } from '../../models/dashboard';
import { findDashboard } from '../../services/dashboard-service';

export default function HeaderContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();

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

  const dataFormatada = dashboard
    ? new Date(dashboard.currentDate).toLocaleDateString('pt-BR', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
      })
    : '';

  return (
    <>
      <p>{dataFormatada}</p>

      <h1>{dashboard?.greeting} 👋</h1>

      <p>
        Você tem <strong>{dashboard?.pendingTasks}</strong> tarefas pendentes,
        sendo <strong>{dashboard?.highPriorityTasks}</strong> de alta
        prioridade.
      </p>
    </>
  );
}
