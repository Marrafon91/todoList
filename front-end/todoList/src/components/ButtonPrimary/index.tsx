import './style.css';

type Props = {
  text: string;
  onClick?: () => void;
  type?: 'button' | 'submit';
  disabled?: boolean;
};

export default function ButtonPrimary({
  text,
  onClick,
  type = 'button',
  disabled,
}: Props) {
  return (
    <button
      className="btn btn-primary"
      type={type}
      onClick={onClick}
      disabled={disabled}
    >
      {text}
    </button>
  );
}
