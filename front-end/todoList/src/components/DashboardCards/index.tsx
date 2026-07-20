import DashboardCard from '../DashboardCard';

import type { DashboardDTO } from '../../models/dashboard';

import './style.css';

type Props = {
  dashboard: DashboardDTO;
};

const colors: Record<string, string> = {
  Total: '#111827',
  Pendentes: '#F59E0B',
  Concluídas: '#22C55E',
  'Alta prioridade': '#EF4444',
};

export default function DashboardCards({ dashboard }: Props) {
  return (
    <div className="dashboard-cards">
      {dashboard.cards.map((card) => (
        <DashboardCard
          key={card.title}
          title={card.title}
          value={card.value}
          color={colors[card.title]}
        />
      ))}
    </div>
  );
}
