import { useEffect, useState } from 'react';
import axios from 'axios';

import './style.css';

import type { CategoryDTO } from '../../models/category';
import type { TaskDTO, TaskInsertDTO, TaskUpdateDTO } from '../../models/task';

import type { ValidationErrorResponse } from '../../models/error';

import { findAllCategories } from '../../services/category-service';
import { useDashboard } from '../../context/DashboardContext';

import ButtonSecondary from '../ButtonSecondary';
import ButtonPrimary from '../ButtonPrimary';

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

  useEffect(() => {
    if (!open) {
      return;
    }

    loadCategories();

    if (taskToEdit) {
      setTask({
        title: taskToEdit.title,
        description: taskToEdit.description,
        priority: taskToEdit.priority,
        categoryId: taskToEdit.category.id,
        dueDate: taskToEdit.dueDate,
      });

      setErrors({});
    } else {
      clearForm();
    }
  }, [open, taskToEdit]);

  async function loadCategories() {
    try {
      const response = await findAllCategories();
      setCategories(response.data);
    } catch (error: unknown) {
      console.error('Erro ao carregar categorias:', error);
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

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();

    setLoading(true);
    setErrors({});

    try {
      if (taskToEdit) {
        const dto: TaskUpdateDTO = {
          title: task.title,
          description: task.description,
          priority: task.priority,
          categoryId: task.categoryId,
          dueDate: task.dueDate,
        };

        await updateTask(taskToEdit.id, dto);
      } else {
        await addTask(task);
      }

      clearForm();
      onClose();
    } catch (error: unknown) {
      if (!axios.isAxiosError<ValidationErrorResponse>(error)) {
        console.error('Erro inesperado:', error);
        return;
      }

      if (error.response?.status === 422) {
        const validationErrors: Record<string, string> = {};

        error.response.data.errors.forEach((item) => {
          validationErrors[item.fieldName] = item.message;
        });

        setErrors(validationErrors);
        return;
      }

      console.error('Erro ao salvar tarefa:', error);
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
            <ButtonSecondary text="Cancelar" onClick={handleCancel} />

            <ButtonPrimary
              text={loading ? 'Salvando...' : 'Salvar'}
              type="submit"
              disabled={loading}
            />
          </div>
        </form>
      </div>
    </div>
  );
}
