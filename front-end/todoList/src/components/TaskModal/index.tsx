import { useEffect, useState } from 'react';
import './style.css';

import type { CategoryDTO } from '../../models/category';
import type { TaskDTO, TaskInsertDTO } from '../../models/task';

import { findAllCategories } from '../../services/category-service';
import { useDashboard } from '../../context/DashboardContext';

type Props = {
  open: boolean;
  onClose: () => void;
  onSaved: (task: TaskDTO) => void;
};

export default function TaskModal({ open, onClose }: Props) {
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<CategoryDTO[]>([]);
  const [errors, setErrors] = useState<Record<string, string>>({});
  const { addTask } = useDashboard();

  const [task, setTask] = useState<TaskInsertDTO>({
    title: '',
    description: '',
    priority: 'LOW',
    categoryId: 0,
    dueDate: '',
  });

  useEffect(() => {
    if (open) {
      loadCategories();
    }
  }, [open]);

  async function loadCategories() {
    try {
      const response = await findAllCategories();
      setCategories(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  function handleChange(
    event: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >,
  ) {
    const { name, value } = event.target;

    setTask((previous) => ({
      ...previous,
      [name]: name === 'categoryId' ? Number(value) : value,
    }));

    if (errors[name]) {
      setErrors((previous) => ({
        ...previous,
        [name]: '',
      }));
    }
  }

  function clearForm() {
    setTask({
      title: '',
      description: '',
      priority: 'LOW',
      categoryId: 0,
      dueDate: '',
    });

    setErrors({});
  }

  async function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    setLoading(true);
    setErrors({});

    try {
      await addTask(task);
      clearForm();
      onClose();
    } catch (error: any) {
      if (error.response?.status === 422) {
        const validationErrors: Record<string, string> = {};

        error.response.data.errors.forEach((item: any) => {
          validationErrors[item.fieldName] = item.message;
        });

        setErrors(validationErrors);
      } else {
        console.log(error);
      }
    } finally {
      setLoading(false);
    }
  }

  function handleCancel() {
    clearForm();
    setErrors({});
    onClose();
  }

  if (!open) {
    return null;
  }

  return (
    <div className="task-modal-overlay" onClick={onClose}>
      <div className="task-modal" onClick={(event) => event.stopPropagation()}>
        <div className="modal-header">
          <h2>Nova tarefa</h2>
          <p>Preencha os dados abaixo.</p>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="title">
              Título <span>*</span>
            </label>

            <input
              id="title"
              name="title"
              value={task.title}
              onChange={handleChange}
              className={errors.title ? 'input-error' : ''}
              placeholder="Digite o título da tarefa"
            />

            {errors.title && <small>{errors.title}</small>}
          </div>

          <div className="form-group">
            <label htmlFor="description">
              Descrição <span>*</span>
            </label>

            <textarea
              id="description"
              rows={4}
              name="description"
              value={task.description}
              onChange={handleChange}
              className={errors.description ? 'input-error' : ''}
              placeholder="Descreva a tarefa"
            />

            {errors.description && <small>{errors.description}</small>}
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="categoryId">
                Categoria <span>*</span>
              </label>

              <select
                id="categoryId"
                name="categoryId"
                value={task.categoryId}
                onChange={handleChange}
                className={errors.categoryId ? 'input-error' : ''}
              >
                <option value={0}>Selecione uma categoria</option>

                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                ))}
              </select>

              {errors.categoryId && <small>{errors.categoryId}</small>}
            </div>

            <div className="form-group">
              <label htmlFor="priority">
                Prioridade <span>*</span>
              </label>

              <select
                id="priority"
                name="priority"
                value={task.priority}
                onChange={handleChange}
              >
                <option value="LOW">🟢 Baixa</option>
                <option value="MEDIUM">🟡 Média</option>
                <option value="HIGH">🔴 Alta</option>
              </select>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="dueDate">
              Data de vencimento <span>*</span>
            </label>

            <input
              id="dueDate"
              type="date"
              name="dueDate"
              value={task.dueDate}
              onChange={handleChange}
              className={errors.dueDate ? 'input-error' : ''}
            />

            {errors.dueDate && <small>{errors.dueDate}</small>}
          </div>

          <div className="modal-buttons">
            <button type="button" className="btn-cancel" onClick={handleCancel}>
              Cancelar
            </button>

            <button type="submit" className="btn-save" disabled={loading}>
              {loading ? 'Salvando...' : 'Salvar'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
