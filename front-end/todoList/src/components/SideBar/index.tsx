import {
  ListTodo,
  Clock3,
  CircleCheck,
  AlertCircle,
  Inbox,
} from 'lucide-react';

import './style.css';

import { useDashboard } from '../../context/DashboardContext';

import SidebarItem from '../SidebarItem';
import CategoryItem from '../CategoryItem';

export default function Sidebar() {
  const { sidebar, filters, setFilters } = useDashboard();

  if (!sidebar) {
    return null;
  }

  return (
    <aside className="sidebar">
      <div className="sidebar-header">
        <div className="sidebar-logo">
          <ListTodo size={24} />
        </div>

        <div>
          <h2 className="sidebar-title-main">Tarefas</h2>
          <p className="sidebar-subtitle">Organize seu dia</p>
        </div>
      </div>

      <div className="sidebar-menu">
        <SidebarItem
          icon={<Inbox size={18} />}
          title="Todas"
          quantity={sidebar.totalTasks}
          active={
            filters.done === undefined &&
            filters.priority === undefined &&
            filters.categoryId === undefined
          }
          onClick={() =>
            setFilters({
              ...filters,
              done: undefined,
              priority: undefined,
              categoryId: undefined,
            })
          }
        />

        <SidebarItem
          icon={<Clock3 size={18} />}
          title="Pendentes"
          quantity={sidebar.pendingTasks}
          active={filters.done === false}
          onClick={() =>
            setFilters({
              ...filters,
              done: false,
              priority: undefined,
              categoryId: undefined,
            })
          }
        />

        <SidebarItem
          icon={<CircleCheck size={18} />}
          title="Concluídas"
          quantity={sidebar.completedTasks}
          active={filters.done === true}
          onClick={() =>
            setFilters({
              ...filters,
              done: true,
              priority: undefined,
              categoryId: undefined,
            })
          }
        />

        {sidebar.priorities.map((priority) => (
          <SidebarItem
            key={priority.priority}
            icon={<AlertCircle size={18} />}
            title={priority.label}
            quantity={priority.quantity}
            active={filters.priority === priority.priority}
            onClick={() =>
              setFilters({
                ...filters,
                priority: priority.priority,
                done: undefined,
                categoryId: undefined,
              })
            }
          />
        ))}
      </div>

      <h3 className="sidebar-category-title">CATEGORIAS</h3>

      <div className="sidebar-categories">
        {sidebar.categories.map((category) => (
          <CategoryItem
            key={category.id}
            title={category.name}
            color={category.color}
            quantity={category.quantity}
            active={filters.categoryId === category.id}
            onClick={() =>
              setFilters({
                ...filters,
                categoryId: category.id,
                done: undefined,
                priority: undefined,
              })
            }
          />
        ))}
      </div>
    </aside>
  );
}
