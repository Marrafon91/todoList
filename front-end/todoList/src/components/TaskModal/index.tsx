import { useEffect, useState } from 'react';
import './style.css';

import type { CategoryDTO } from '../../models/category';
import type { TaskDTO, TaskInsertDTO, TaskUpdateDTO } from '../../models/task';

import { findAllCategories } from '../../services/category-service';
import { useDashboard } from '../../context/DashboardContext';

type Props = {
  open: boolean;
  onClose: () => void;
  taskToEdit: TaskDTO | null;
};

export default function TaskModal({ open, onClose, taskToEdit }: Props) {
  const [loading, setLoading] = useState(false);
  const [categories, setCategories] = useState<CategoryDTO[]>([]);
  const [errors, setErrors] = useState<Record<string, string>>({});

  const { addTask, updateTask } = useDashboard();

  const [task, setTask] = useState<TaskInsertDTO>({
    title: '',
    description: '',
    priority: 'LOW',
    categoryId: 0,
    dueDate: '',
  });

  async function loadCategories() {
    try {
      const response = await findAllCategories();
      setCategories(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    if (!open) return;

    loadCategories();

    if (taskToEdit) {
      setTask({
        title: taskToEdit.title,
        description: taskToEdit.description,
        priority: taskToEdit.priority,
        categoryId: taskToEdit.category.id,
        dueDate: taskToEdit.dueDate,
      });
    } else {
      clearForm();
    }
  }, [open, taskToEdit]);

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
      if (taskToEdit) {
        const dto: TaskUpdateDTO = {
          ...task,
          done: taskToEdit.done,
        };

        await updateTask(taskToEdit.id, dto);
      } else {
        await addTask(task);
      }

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
    onClose();
  }

  if (!open) {
    return null;
  }

  return (
    <div className="task-modal-overlay" onClick={handleCancel}>
      <div className="task-modal" onClick={(event) => event.stopPropagation()}>
        <div className="modal-header">
          <h2>{taskToEdit ? 'Editar tarefa' : 'Nova tarefa'}</h2>
          <p>
            {taskToEdit
              ? 'Atualize as informações da tarefa.'
              : 'Preencha os dados abaixo.'}
          </p>
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
              {loading ? 'Salvando...' : taskToEdit ? 'Atualizar' : 'Salvar'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
