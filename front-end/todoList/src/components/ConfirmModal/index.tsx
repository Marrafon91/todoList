import ButtonDanger from '../ButtonDanger';
import ButtonSecondary from '../ButtonSecondary';
import './style.css';

type Props = {
  open: boolean;
  title?: string;
  message: string;
  onConfirm: () => void;
  onCancel: () => void;
};

export default function ConfirmModal({
  open,
  title,
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
        <h2>{title ?? 'Confirmar exclusão'}</h2>

        <p>{message}</p>

        <div>
          <ButtonSecondary text="Cancelar" onClick={onCancel} />

          <ButtonDanger text="Excluir" onClick={onConfirm} />
        </div>
      </div>
    </div>
  );
}
