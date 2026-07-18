import { useEffect, useState } from 'react';
import { ListTodo, Clock3, CircleCheck, AlertCircle } from 'lucide-react';

import './style.css';

import type { SidebarDTO } from '../../models/sidebar';
import { findSidebar } from '../../services/sidebar-service';

import SidebarItem from '../SidebarItem';
import CategoryItem from '../CategoryItem';

export default function Sidebar() {
  const [sidebar, setSidebar] = useState<SidebarDTO>();

  useEffect(() => {
    async function loadSidebar() {
      try {
        const response = await findSidebar();
        setSidebar(response.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadSidebar();
  }, []);

  return (
    <aside className="sidebar">
      {/* Cabeçalho */}
      <div className="sidebar-header">
        <div className="sidebar-logo">
          <ListTodo size={24} />
        </div>

        <div>
          <h2 className="sidebar-title-main">Tarefas</h2>
          <p className="sidebar-subtitle">Organize seu dia</p>
        </div>
      </div>

      {/* Resumo */}
      <div className="sidebar-menu">
        <SidebarItem
          icon={<ListTodo size={18} />}
          title="Todas"
          quantity={sidebar?.totalTasks ?? 0}
          active
        />

        <SidebarItem
          icon={<Clock3 size={18} />}
          title="Pendentes"
          quantity={sidebar?.pendingTasks ?? 0}
        />

        <SidebarItem
          icon={<CircleCheck size={18} />}
          title="Concluídas"
          quantity={sidebar?.completedTasks ?? 0}
        />

        {sidebar?.priorities.map((priority) => (
          <SidebarItem
            key={priority.priority}
            icon={<AlertCircle size={18} />}
            title={priority.label}
            quantity={priority.quantity}
          />
        ))}
      </div>

      {/* Categorias */}
      <h3 className="sidebar-category-title">CATEGORIAS</h3>

      <div className="sidebar-categories">
        {sidebar?.categories.map((category) => (
          <CategoryItem
            key={category.id}
            title={category.name}
            color={category.color}
            quantity={category.quantity}
          />
        ))}
      </div>
    </aside>
  );
}
