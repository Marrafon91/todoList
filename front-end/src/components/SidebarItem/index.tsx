import './style.css';

import AppTooltip from '../Tooltip';
import { useDashboard } from '../../context/DashboardContext';

type Props = {
  icon: React.ReactNode;
  title: string;
  quantity: number;
  active?: boolean;
  onClick?: () => void;
};

export default function SidebarItem({
  icon,
  title,
  quantity,
  active = false,
  onClick,
}: Props) {
  const { sidebarOpen } = useDashboard();

  return (
    <AppTooltip content={title} disabled={sidebarOpen}>
      <div
        className={`sidebar-item ${active ? 'active' : ''}`}
        onClick={onClick}
      >
        <div className="sidebar-left">
          {icon}

          {sidebarOpen && <span>{title}</span>}
        </div>

        {sidebarOpen && <span className="sidebar-count">{quantity}</span>}
      </div>
    </AppTooltip>
  );
}
