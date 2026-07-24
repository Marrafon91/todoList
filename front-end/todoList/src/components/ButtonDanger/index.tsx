import './style.css';

type Props = {
  text: string;
  onClick?: () => void;
};

export default function ButtonDanger({ text, onClick }: Props) {
  return (
    <button className="btn btn-danger" onClick={onClick}>
      {text}
    </button>
  );
}
