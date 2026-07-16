import './style.css';
import type { ReactNode } from 'react';

type Props = {
  icon: ReactNode;
  title: string;
  quantity: number;
  active?: boolean;
};

export default function SidebarItem({
  icon,
  title,
  quantity,
  active = false,
}: Props) {
  return (
    <div className={active ? 'sidebar-item active' : 'sidebar-item'}>
      <div className="sidebar-item-left">
        {icon}

        <span>{title}</span>
      </div>

      <span>{quantity}</span>
    </div>
  );
}
