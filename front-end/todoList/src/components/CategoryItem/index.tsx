import "./style.css";

type Props = {
  title: string;
  color: string;
  quantity: number;
};

export default function CategoryItem({
  title,
  color,
  quantity,
}: Props) {
  return (
    <div className="category-item">
      <div className="category-left">
        <span
          className="category-color"
          style={{ backgroundColor: color }}
        />

        <span>{title}</span>
      </div>

      <span className="category-quantity">{quantity}</span>
    </div>
  );
}
