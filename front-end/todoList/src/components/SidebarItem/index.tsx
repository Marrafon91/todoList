import './style.css';

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
  return (
    <button
      className={`sidebar-item ${active ? 'active' : ''}`}
      onClick={onClick}
    >
      <div className="sidebar-item-left">
        {icon}
        <span>{title}</span>
      </div>

      <span className="sidebar-item-count">{quantity}</span>
    </button>
  );
}
