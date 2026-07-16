import './style.css';

type Props = {
  icon: React.ReactNode;
  title: string;
  quantity: number;
};

export default function SidebarItem({ icon, title, quantity }: Props) {
  return (
    <div className="sidebar-item">
      <div className="sidebar-left">
        {icon}
        <span>{title}</span>
      </div>

      <span>{quantity}</span>
    </div>
  );
}
