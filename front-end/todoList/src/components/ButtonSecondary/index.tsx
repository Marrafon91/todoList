import './style.css';

type Props = {
  text: string;
  onClick?: () => void;
};

export default function ButtonSecondary({ text, onClick }: Props) {
  return (
    <button className="btn btn-secondary" onClick={onClick}>
      {text}
    </button>
  );
}
