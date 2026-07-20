import type { DashboardDTO } from '../../models/dashboard';

import './style.css';

type Props = {
  dashboard: DashboardDTO;
};

export default function HeaderContent({ dashboard }: Props) {
  const date = new Date(`${dashboard.currentDate}T00:00:00`);

  const currentDate = date.toLocaleDateString('pt-BR', {
    weekday: 'long',
    day: 'numeric',
    month: 'long',
  });

  return (
    <header className="header-content">
      <span>{currentDate}</span>

      <h1>{dashboard.greeting} 👋</h1>

      <p>
        Você tem <strong>{dashboard.pendingTasks}</strong> tarefas pendentes,
        sendo <strong>{dashboard.highPriorityTasks}</strong> de alta prioridade.
      </p>
    </header>
  );
}
