import './style.css';

import SidebarHeader from '../SidebarHeader/Index';
import SidebarItem from '../SidebarItem';
import CategoryItem from '../CategoryItem';

import { ListTodo, Clock3, CircleCheck, AlertCircle } from 'lucide-react';

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <SidebarHeader />

      <nav>
        <SidebarItem
          icon={<ListTodo size={18} />}
          title="Todas"
          quantity={7}
          active
        />

        <SidebarItem
          icon={<Clock3 size={18} />}
          title="Pendentes"
          quantity={5}
        />

        <SidebarItem
          icon={<CircleCheck size={18} />}
          title="Concluídas"
          quantity={2}
        />

        <SidebarItem
          icon={<AlertCircle size={18} />}
          title="Alta prioridade"
          quantity={2}
        />
      </nav>

      <h3 className="sidebar-title">Categorias</h3>

      <CategoryItem color="#2563EB" title="Trabalho" quantity={4} />

      <CategoryItem color="#22C55E" title="Estudos" quantity={2} />

      <CategoryItem color="#F59E0B" title="Pessoal" quantity={0} />

      <CategoryItem color="#EF4444" title="Casa" quantity={1} />
    </aside>
  );
}
