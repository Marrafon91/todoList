import './style.css';

type Props = {
  title: string;
  color: string;
  quantity: number;
  active?: boolean;
  onClick?: () => void;
};

export default function CategoryItem({
  title,
  color,
  quantity,
  active = false,
  onClick,
}: Props) {
  return (
    <button
      className={`category-item ${active ? 'active' : ''}`}
      onClick={onClick}
    >
      <div className="category-left">
        <span className="category-color" style={{ backgroundColor: color }} />

        <span>{title}</span>
      </div>

      <span>{quantity}</span>
    </button>
  );
}
