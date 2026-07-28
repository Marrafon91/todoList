import './style.css';

import AppTooltip from '../Tooltip';
import { useDashboard } from '../../context/DashboardContext';

type Props = {
  title: string;
  color: string;
  quantity: number;
  active?: boolean;
  onClick?: () => void;
};

export default function CategoryItem({
  title,
  color,
  quantity,
  active = false,
  onClick,
}: Props) {
  const { sidebarOpen } = useDashboard();

  return (
    <AppTooltip content={title} disabled={sidebarOpen}>
      <div
        className={`category-item ${active ? 'active' : ''}`}
        onClick={onClick}
      >
        <div className="category-left">
          <span className="category-color" style={{ backgroundColor: color }} />

          {sidebarOpen && <span>{title}</span>}
        </div>

        {sidebarOpen && <span className="category-quantity">{quantity}</span>}
      </div>
    </AppTooltip>
  );
}
