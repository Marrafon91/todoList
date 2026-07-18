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
    ? (() => {
        const data = new Date(`${dashboard.currentDate}T00:00:00`);

        const texto = data.toLocaleDateString('pt-BR', {
          weekday: 'long',
          day: 'numeric',
          month: 'long',
        });

        return texto.charAt(0).toUpperCase() + texto.slice(1);
      })()
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
