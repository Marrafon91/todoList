import './style.css';

type Props = {
  icon: React.ReactNode;
  title: string;
  quantity: number;
  active?: boolean;
};

export default function SidebarItem({ icon, title, quantity, active }: Props) {
  return (
    <div className={`sidebar-item ${active ? 'active' : ''}`}>
      <div className="sidebar-left">
        {icon}
        <span>{title}</span>
      </div>

      <span>{quantity}</span>
    </div>
  );
}
