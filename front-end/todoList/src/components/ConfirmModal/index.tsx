import './style.css';

type Props = {
  open: boolean;
  message: string;
  onConfirm: () => void;
  onCancel: () => void;
};

export default function ConfirmModal({
  open,
  message,
  onConfirm,
  onCancel,
}: Props) {
  if (!open) {
    return null;
  }

  return (
    <div className="modal-overlay" onClick={onCancel}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <h2>Confirmar exclusão</h2>

        <p>{message}</p>

        <div>
          <button className="cancel-button" onClick={onCancel}>
            Cancelar
          </button>

          <button className="delete-confirm-button" onClick={onConfirm}>
            Excluir
          </button>
        </div>
      </div>
    </div>
  );
}
