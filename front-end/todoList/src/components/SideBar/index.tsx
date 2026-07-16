import './style.css';

import { useEffect, useState } from 'react';

import SidebarHeader from '../SidebarHeader';
import SidebarItem from '../SidebarItem';
import CategoryItem from '../CategoryItem';

import { ListTodo, Clock3, CircleCheck, AlertCircle } from 'lucide-react';

import { findAllCategories } from '../../services/category-service';
import type { CategoryDTO } from '../../models/category';

export default function Sidebar() {
  const [categories, setCategories] = useState<CategoryDTO[]>([]);

  useEffect(() => {
    async function loadCategories() {
      try {
        const response = await findAllCategories();
        setCategories(response.data);
      } catch (error) {
        console.log(error);
      }
    }

    loadCategories();
  }, []);

  return (
    <aside className="sidebar">
      <SidebarHeader />

      <SidebarItem
        icon={<ListTodo size={18} />}
        title="Todas"
        quantity={7}
        active
      />

      <SidebarItem icon={<Clock3 size={18} />} title="Pendentes" quantity={5} />

      <SidebarItem
        icon={<CircleCheck size={18} />}
        title="Concluídas"
        quantity={2}
      />

      <SidebarItem
        icon={<AlertCircle size={18} />}
        title="Alta prioridade"
        quantity={1}
      />

      <h3 className="sidebar-title">Categorias</h3>

      {categories.map((category) => (
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
