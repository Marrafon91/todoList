import './style.css';

type Props = {
  open: boolean;
  onClose: () => void;
};

export default function TaskModal({ open, onClose }: Props) {
  if (!open) {
    return null;
  }

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <h2>Nova tarefa</h2>

        <p>Conteúdo do formulário virá aqui.</p>

        <button onClick={onClose}>Fechar</button>
      </div>
    </div>
  );
}
