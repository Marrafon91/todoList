import { useEffect, useState } from 'react';
import './style.css';
import type { DashboardDTO } from '../../models/dashboard';
import { findDashboard } from '../../services/dashboard-service';

export default function HeaderContent() {
  const [dashboard, setDashboard] = useState<DashboardDTO>();
  const [error, setError] = useState('');

  useEffect(() => {
    async function loadDashboard() {
      try {
        const response = await findDashboard();
        setDashboard(response.data);
      } catch {
        setError('Não foi possível carregar as tarefas.');
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

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  return (
    <div className="header-content">
      <span className="header-date">{dataFormatada}</span>

      <h1 className="header-title">
        {dashboard?.greeting} <span>👋</span>
      </h1>

      <p className="header-description">
        Você tem <strong>{dashboard?.pendingTasks}</strong> tarefas pendentes,
        sendo <strong>{dashboard?.highPriorityTasks}</strong> de alta
        prioridade.
      </p>

      {error && <p className="error-message">{error}</p>}
    </div>
  );
}
