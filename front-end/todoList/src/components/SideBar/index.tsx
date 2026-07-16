import { useEffect, useState } from 'react';

import { ListTodo, Clock3, CircleCheck, AlertCircle } from 'lucide-react';

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
    <aside>
      <SidebarItem
        icon={<ListTodo size={18} />}
        title="Todas"
        quantity={sidebar?.totalTasks ?? 0}
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

      <h3>Categorias</h3>

      {sidebar?.categories.map((category) => (
        <CategoryItem
          key={category.id}
          title={category.name}
          color={category.color}
          quantity={category.quantity}
        />
      ))}
    </aside>
  );
}
